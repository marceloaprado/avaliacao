import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { Observable } from 'rxjs/internal/Observable';
import { catchError, debounceTime, map, switchMap } from 'rxjs/operators';
import { Combo } from 'src/app/models/combo';
import { Marca } from 'src/app/models/marca';
import { Veiculo } from 'src/app/models/veiculo';
import { RestService } from 'src/app/services/rest.service';
import { Constants } from 'src/app/utils/constants.enum';
import { Utils } from 'src/app/utils/utils';
import { AlertService } from '../alert/_services';
import { BaseComponent } from '../base-component';
import { ErrorDialogComponent } from '../dialogs/errordialog/errordialog.component';
import { QuestionDialogComponent } from '../dialogs/questiondialog/questiondialog.component';

@Component({
  selector: 'app-veiculos',
  templateUrl: './veiculos.component.html',
  styleUrls: ['./veiculos.component.scss']
})
export class VeiculosComponent extends BaseComponent implements OnInit {
  public mensagemValidacao = "";
  public veiculos: Veiculo[] = [];
  public displayedColumns: string[] = ["veiculo", "marca", "ano", "vendido", "editar", "excluir"];    
  public dataSource: MatTableDataSource<Veiculo>;
  public dialogRef: MatDialogRef<QuestionDialogComponent>;
  public filtroPesquisa = "";
  public modificado = false;
  public veiculoEditado: Veiculo;
  public statusEditando = false;
  private showLoadingMarcas: boolean = false;
  public marcasFiltradas: Observable<Combo[]>;

  constructor(
    private fb: FormBuilder,
    private rest: RestService,
    private alertService: AlertService,
    private router: Router,
    private dialog: MatDialog) {
    super();

    this.formulario = fb.group({
      "id": new FormControl({ value: "", disabled: true }),
      "veiculo": new FormControl(""),
      "marca": new FormControl(""),
      "ano": new FormControl(""),
      "descricao": new FormControl(""),
      "vendido": new FormControl(false)
    });

    this.dataSource = new MatTableDataSource<Veiculo>(this.veiculos);
  }

  ngOnInit(): void {
    this.pesquisar();
    this.autoCompleteMarcas();
    this.addFormValidations();
  }

  public addFormValidations(): void {
    this.formulario.get("veiculo").setValidators([Validators.required, Validators.maxLength(100)]);
    this.formulario.get("marca").setValidators([Validators.required]);
    this.formulario.get("ano").setValidators([Validators.required]);
    this.formulario.get("descricao").setValidators([Validators.required]);
    this.formulario.get("vendido").setValidators([Validators.required]);
    this.formulario.updateValueAndValidity();
    this.formulario.get("veiculo").updateValueAndValidity();
    this.formulario.get("marca").updateValueAndValidity();
    this.formulario.get("ano").updateValueAndValidity();
    this.formulario.get("descricao").updateValueAndValidity();
    this.formulario.get("vendido").updateValueAndValidity();
  }

  public removeFormValidations(): void {
    this.formulario.get("veiculo").clearValidators();
    this.formulario.get("marca").clearValidators();
    this.formulario.get("ano").clearValidators();
    this.formulario.get("descricao").clearValidators();
    this.formulario.get("vendido").clearValidators();
    this.formulario.updateValueAndValidity();
    this.formulario.get("veiculo").updateValueAndValidity();
    this.formulario.get("marca").updateValueAndValidity();
    this.formulario.get("ano").updateValueAndValidity();
    this.formulario.get("descricao").updateValueAndValidity();
    this.formulario.get("vendido").updateValueAndValidity();
  }

  public requestListarVeiculos(filtros: string = ""): void {
    this.lockForm();

    this.rest.getRequestUnsign(Constants.API_URL_VEICULOS + filtros).subscribe(
      (response) => {
        if (response.length === 0) {
          this.alertService.clear();
          this.alertService.info("Nenhum registro foi encontrado");
        }
        this.unlockForm();
        this.veiculos = response;
        this.dataSource.data = this.veiculos;
      },
      (error) => {
        this.unlockForm();
        this.dataSource.data = [];
        this.alertService.clear();
        this.alertService.error(error.message);
      }
    );
  }

  public requestSalvarVeiculo(): void {
    this.lockForm();

    const request = {
      "veiculo": this.formulario.get("veiculo").value,
      "ano": this.formulario.get("ano").value,
      "descricao": this.formulario.get("descricao").value,
      "marca": this.formulario.get("marca").value,
      "vendido": this.formulario.get("vendido").value
    };

    this.rest.postRequestUnsign(Constants.API_URL_VEICULOS, request).subscribe(
      (response) => {
        this.unlockForm();
        this.showProgressBar = false;
        this.limpar();
        this.requestListarVeiculos();
        this.alertService.success("Veículo registrado com sucesso!");
      },
      (error) => {
        this.unlockForm();
        this.alertService.clear();
        this.alertService.error(error.message);
      }
    );
  }

  public requestAtualizarVeiculo(): void {
    this.lockForm();

    const request = {
      "veiculo": this.formulario.get("veiculo").value,
      "ano": this.formulario.get("ano").value,
      "descricao": this.formulario.get("descricao").value,
      "marca": this.formulario.get("marca").value,
      "vendido": this.formulario.get("vendido").value
    };

    let restOperation: Observable<any>;

    if (Utils.isNotEmpty(request.veiculo) && Utils.isNotEmpty(request.ano) && Utils.isNotEmpty(request.descricao) && Utils.isNotEmpty(request.marca) && Utils.isNotEmpty(request.vendido))
      restOperation = this.rest.putRequestUnsign(Constants.API_URL_VEICULOS + "/" + this.veiculoEditado.id, request);
    else
      restOperation = this.rest.patchRequestUnsign(Constants.API_URL_VEICULOS + "/" + this.veiculoEditado.id, request);

    restOperation.subscribe(
      (response) => {        
        this.unlockForm();
        this.showProgressBar = false;

        console.log(request);
        
        this.alertService.clear();
        this.veiculoEditado.veiculo = request.veiculo;
        this.veiculoEditado.ano = request.ano;
        this.veiculoEditado.marca = { marca: request.marca.descricao, id: request.marca.id };
        this.veiculoEditado.descricao = request.descricao;
        this.veiculoEditado.vendido = request.vendido;

        this.pesquisar();
        
        this.alertService.success("Veículo atualizado com sucesso!");
      },
      (error) => {
        this.unlockForm();
        this.alertService.clear();
        this.alertService.error(error.message);
      }
    );
  }

  public requestExcluirVeiculo(id: number): void {
    this.lockForm();
    this.rest.deleteRequestUnsign(Constants.API_URL_VEICULOS + "/" + id).subscribe(
      (response) => {
        this.requestListarVeiculos();
        this.unlockForm();
        this.alertService.clear();

        this.showProgressBar = false;
        this.alertService.success("Veículo excluído com sucesso!");

      },
      (error) => {
        this.unlockForm();
        this.alertService.clear();
        this.alertService.error(error.message);
      }
    );
  }

  private requestListarMarcas(search: string): Observable<any> {
    this.showLoadingMarcas = true;
    return this.rest.getRequestUnsign(Constants.API_URL_MARCAS + "?search=" + search).pipe(
      map((response) => response.content.map((obj: Marca) => new Combo(obj.id, obj.marca))),
      catchError(_ => {
        return of(null);
      })
    );
  }

  public getErrorMessage(nomeCampo?: string, formGroup?: FormGroup) {
    formGroup = Utils.isNullOrUndefined(formGroup) ? this.formulario : formGroup;

    let campo: any = formGroup.get(nomeCampo);

    if (Utils.isNullOrUndefined(campo))
      return "";

    return campo.hasError("required") ? "Campo obrigatório" :
      campo.hasError("maxlength") ? "Tamanho máximo de caracteres excedido" : ""
  }

  public isFormLocked(): boolean {
    return this.formLocked;
  }

  public enviarRequisicaoEnter(): void {
    if (this.formulario.valid) {
      this.requestSalvarVeiculo();
    }
  }

  public close(): void {
    this.router.navigate([""]);
  }

  public limpar() {
    this.statusEditando = false;
    this.addFormValidations();
    this.modificado = false;
    this.veiculoEditado = null;
    this.formulario.reset();
    this.formulario.get("vendido").setValue(false);
    this.alertService.clear();
    this.pesquisar();
  }

  public pesquisar() {
    this.filtroPesquisa = "";

    if (Utils.isNotEmpty(this.formulario.get("veiculo").value)) {
      this.filtroPesquisa += "&veiculo=" + this.formulario.get("veiculo").value;
    }

    if (Utils.isNotEmpty(this.formulario.get("ano").value)) {
      this.filtroPesquisa += "&ano=" + this.formulario.get("ano").value;
    }

    if (Utils.isNotEmpty(this.formulario.get("marca").value)) {
      this.filtroPesquisa += "&marca=" + this.formulario.get("marca").value.descricao;
    }

    if (Utils.isNotEmpty(this.formulario.get("vendido").value)) {
      this.filtroPesquisa += "&vendido=" + this.formulario.get("vendido").value;
    }

    this.filtroPesquisa = Utils.isNotEmpty(this.filtroPesquisa) ? "/find?" + this.filtroPesquisa.substr(1, this.filtroPesquisa.length) : "";
    this.requestListarVeiculos(this.filtroPesquisa);
  }

  public salvarVeiculo() {
    if (!this.statusEditando) {
      this.requestSalvarVeiculo();
    } else {
      this.requestAtualizarVeiculo();
    }
    this.modificado = false;
  }


  public editarVeiculo(veiculo: Veiculo) {
    this.veiculoEditado = veiculo;
    this.preencheCampos(this.veiculoEditado);
  }

  public excluirVeiculo(veiculo: Veiculo) {
    const message = "Deseja realmente excluir o veículo? Após confirmar, não será possível desfazer esta operação.";

    this.dialogRef = this.dialog.open(QuestionDialogComponent, {
      width: "500px",
      data: {
        questionMessageData: {
          title: "Atenção",
          body: message
        }
      }
    });

    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.requestExcluirVeiculo(veiculo.id);
      }
    });
  }

  public desabilitaSalvar(): boolean {
    return (!this.statusEditando && (this.formulario.invalid || (!Utils.isNullOrUndefined(this.formulario.get("marca").value) && Utils.isNullOrUndefined(this.formulario.get("marca").value.id)))) || this.isFormLocked();
  }

  public preencheCampos(veiculo: Veiculo): void {
    this.formulario.get("id").setValue(veiculo.id);
    this.formulario.get("veiculo").setValue(veiculo.veiculo);
    this.formulario.get("marca").setValue({ id: veiculo.marca.id, descricao: veiculo.marca.marca });
    this.formulario.get("ano").setValue(veiculo.ano);
    this.formulario.get("descricao").setValue(veiculo.descricao);
    this.formulario.get("vendido").setValue(veiculo.vendido);

    this.statusEditando = true;
    this.removeFormValidations();
  }

  public autoCompleteMarcas() {
    this.marcasFiltradas = this.formulario.get("marca").valueChanges
      .pipe(
        debounceTime(300),
        switchMap((value: string) => {
          if (Utils.isNotEmpty(value)) {
            return this.requestListarMarcas(value);
          } else {
            return of(null);
          }
        })
      );
  }

  displayMarcas(combo: Combo) {
    if (combo) { return combo.descricao; }
  }
}
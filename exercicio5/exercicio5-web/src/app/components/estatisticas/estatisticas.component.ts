import { Component, OnInit } from '@angular/core';
import { Veiculo } from 'src/app/models/veiculo';
import { RestService } from 'src/app/services/rest.service';
import { Constants } from 'src/app/utils/constants.enum';
import { AlertService } from '../alert/_services';
import * as moment from 'moment';
import { groupBy, Utils } from 'src/app/utils/utils';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-estatisticas',
  templateUrl: './estatisticas.component.html',
  styleUrls: ['./estatisticas.component.scss']
})
export class EstatisticasComponent implements OnInit {
  public dataSource: MatTableDataSource<Veiculo>;
  public displayedColumns: string[] = ["veiculo", "marca", "ano", "vendido"];
  public qtdVeiculosNaoVendidos: number = 0;
  public qtdVeiculosUltimaSemana: number = 0;
  public marcaVeiculos: any[];
  public decadaVeiculos: any[];
  public veiculos: Veiculo[];
  public exibirVeiculos: boolean = false;

  constructor(
    private rest: RestService,
    private alertService: AlertService) { 

    this.dataSource = new MatTableDataSource<Veiculo>(this.veiculos);
  }

  ngOnInit(): void {
    this.limparEstatisticas();
    this.carregarEstatisticas();
  }

  public carregarEstatisticas() {
    this.limparEstatisticas();
    this.requestGetVeiculosNaoVendidos();
    this.requestGetVeiculosUltimaSemana();
    this.requestGetVeiculos();
  }

  public limparEstatisticas(): void {
    this.qtdVeiculosNaoVendidos = 0
    this.qtdVeiculosUltimaSemana = 0;
    this.marcaVeiculos = [];
    this.decadaVeiculos = [];
    this.veiculos = [];
  }

  public requestGetVeiculosNaoVendidos(): void {
    this.rest.getRequestUnsign(Constants.API_URL_VEICULOS + "/find?vendido=false").subscribe(
      (response) => {        
        this.qtdVeiculosNaoVendidos = response.length;
      },
      (error) => {
        this.alertService.clear();
        this.alertService.error(error.message);
      }
    );
  }

  public requestGetVeiculosUltimaSemana(): void {    
    let inicioIntervalo = moment().subtract(7, "days").format("DD/MM/YYYY");
    let fimIntervalo = moment().format("DD/MM/YYYY");

    this.rest.getRequestUnsign(Constants.API_URL_VEICULOS + "/find?inicioIntervaloCriacao=" + inicioIntervalo + "&fimIntervaloCriacao=" + fimIntervalo).subscribe(
      (response) => {        
        this.qtdVeiculosUltimaSemana = response.length;
      },
      (error) => {
        this.alertService.clear();
        this.alertService.error(error.message);
      }
    );
  }

  public requestGetVeiculos(): void {
    this.rest.getRequestUnsign(Constants.API_URL_VEICULOS).subscribe(
      (response) => {        
        this.veiculos = response;
        this.veiculos.forEach(obj => this.populateMetricas(obj));
      },
      (error) => {
        this.alertService.clear();
        this.alertService.error(error.message);
      }
    );
  }

  public abrirVeiculosUltimaSemana(): void {
    if(this.exibirVeiculos) {
      this.dataSource.data = [];      
    } else {
      this.dataSource.data = this.veiculos;  
    }

    this.exibirVeiculos = !this.exibirVeiculos;
  }

  private populateMetricas(veiculo: Veiculo): void {
    let indexMarca = this.marcaVeiculos.findIndex((obj: any) => obj.marca === veiculo.marca.marca);
    indexMarca === -1 ? this.marcaVeiculos.push({marca: veiculo.marca.marca, qtd: 1}) : this.marcaVeiculos[indexMarca].qtd++;

    let indexDecada = this.decadaVeiculos.findIndex((obj: any) => obj.decada === this.getDecadaPorAno(veiculo.ano));
    indexDecada === -1 ? this.decadaVeiculos.push({decada: this.getDecadaPorAno(veiculo.ano), qtd: 1}) : this.decadaVeiculos[indexDecada].qtd++;
    this.decadaVeiculos.sort((a, b) => a.decada - b.decada);
  }

  private getDecadaPorAno(ano: number): number {
    return ano - ano % 10;
  }
}

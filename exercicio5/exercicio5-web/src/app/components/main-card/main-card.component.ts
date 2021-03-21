import {
  Component,
  OnInit,
  Input,
  EventEmitter,
  Output
} from "@angular/core";
import { Router } from "@angular/router";
import { MatDialog } from "@angular/material/dialog";
import { QuestionDialogComponent } from '../dialogs/questiondialog/questiondialog.component';

@Component({
  selector: "main-card",
  templateUrl: "./main-card.component.html",
  styleUrls: ["./main-card.component.scss"]
})
export class MainCardComponent implements OnInit {
  @Input() titulo: string;
  @Input() modificado: boolean;
  @Input() verificaAlteracoes = true;
  @Input() exibeBotaoFechar: boolean = true;
  @Input() exibeBotaoAtualizar: boolean = false;  
  @Input() modoDialog: boolean = false;
  @Input() closeRedirect: string = "";
  @Input() showLoading: boolean = false;
  @Output() atualizarEvent = new EventEmitter<any>();

  constructor(private router: Router, private dialog: MatDialog) { }

  ngOnInit() {
    if (this.closeRedirect === null || this.closeRedirect === undefined) 
      this.closeRedirect = "";
  }

  public atualizar(): void {
    this.atualizarEvent.emit();
  }

  public close(): void {
    if (this.modificado && this.verificaAlteracoes) {
      this.dialog
        .open(QuestionDialogComponent, {
          data: {
            questionMessageData: {
              title: "Alterações pendentes",
              body: "Deseja sair da tela sem efetuar as alterações?"
            }
          }
        })
        .afterClosed()
        .subscribe(result => {
          if (result) {
            this.router.navigate([this.closeRedirect]);
          }
        });
    } else {
      this.router.navigate([this.closeRedirect]);
    }
  }
}

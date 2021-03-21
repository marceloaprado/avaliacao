import { Component, Inject } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
  selector: "app-questiondialog",
  templateUrl: "./questiondialog.component.html",
  styleUrls: ["./questiondialog.component.scss"]
})
export class QuestionDialogComponent {

  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<QuestionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }
}

export interface QuestionMessageData {
  title: string;
  body: string;
}

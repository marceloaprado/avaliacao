import { Component, Inject } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
  selector: "app-errordialog",
  templateUrl: "./errordialog.component.html",
  styleUrls: ["./errordialog.component.scss"]
})
export class ErrorDialogComponent {

  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<ErrorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }
}

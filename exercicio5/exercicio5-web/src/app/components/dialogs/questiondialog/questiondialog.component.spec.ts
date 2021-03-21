import { async, ComponentFixture, TestBed } from "@angular/core/testing";

import { QuestionDialogComponent } from "./questiondialog.component";

describe("QuestiondialogComponent", () => {
  let component: QuestionDialogComponent;
  let fixture: ComponentFixture<QuestionDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestionDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});

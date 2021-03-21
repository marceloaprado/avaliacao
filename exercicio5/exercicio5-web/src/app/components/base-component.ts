import { FormGroup } from '@angular/forms';

export class BaseComponent {
    public formLocked = false;
    public showProgressBar = false;
    public formulario: FormGroup;

    public lockForm(): void {
        this.formLocked = true;
        this.formulario.disable();
        this.showLoading();
    }

    public lockSpecificForm(form: FormGroup): void {
        this.formLocked = true;        
        form.disable();
        this.showLoading();
    }
    
    public unlockForm(): void {
        this.formLocked = false;
        this.hideLoading();
        this.formulario.enable();

        if(!(this.formulario.get("id") === null || this.formulario.get("id") === undefined))
            this.formulario.get("id").disable();
    }

    public unlockSpecificForm(form: FormGroup): void {
        this.formLocked = false;
        this.hideLoading();
        form.enable();

        if(!(form.get("id") === null || form.get("id") === undefined))
            form.get("id").disable();
    }

    public isFormLocked(): boolean {
        return this.formLocked;
    }

    public showLoading() {
        this.showProgressBar = true;
    }

    public hideLoading() {
        this.showProgressBar = false;
    }

    public limpar() {
        this.formulario.reset();
    }
}
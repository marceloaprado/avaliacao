import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './components/app/app.component';
import { VeiculosComponent } from './components/veiculos/veiculos.component';
import { MainComponent } from './layouts/main/main.component';
import { CommonModule, registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material/material.module';
import { AppRouting } from './routes/app.routes';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { Injector } from '@angular/core';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from '@angular/material/form-field';
import { MomentDateAdapter, MAT_MOMENT_DATE_FORMATS } from '@angular/material-moment-adapter';
import { MenuItemComponent } from './layouts/main/menus.perfis';
import { AlertComponent } from './components/alert/_directives/alert.component';
import { ErrorDialogComponent } from './components/dialogs/errordialog/errordialog.component';
import { QuestionDialogComponent } from './components/dialogs/questiondialog/questiondialog.component';
import { MainCardComponent } from './components/main-card/main-card.component';
import { EstatisticasComponent } from './components/estatisticas/estatisticas.component';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import localePT from '@angular/common/locales/pt';

registerLocaleData(localePT);

export let AppInjector: Injector;

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    MainCardComponent,
    VeiculosComponent,
    MenuItemComponent,
    AlertComponent,
    ErrorDialogComponent,
    QuestionDialogComponent,
    EstatisticasComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,    
    MaterialModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRouting,
    HttpClientModule,
  ],
  providers: [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },
    { provide: MAT_DATE_LOCALE, useValue: 'pt-br' },
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS },
    { provide: LOCALE_ID, useValue: 'pt-BR' },
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { 
  constructor(private injector: Injector) {
    AppInjector = this.injector;
  }
}
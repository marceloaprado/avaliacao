import { AbstractControl } from "@angular/forms";

/**
 * Classe de funções de uso geral
 */
export class Utils {

  public static pageSizeOptions: number[] = [20, 10, 30];

  public static isNullOrUndefined(valor: any) {
    return (valor === undefined || valor === null);
  }

  public static isEmpty(valor: any) {
    return (valor === "" || Utils.isNullOrUndefined(valor));
  }

  public static isNotEmpty(valor: any) {
    return !Utils.isEmpty(valor);
  }
}

export function isObjectValidator(control: AbstractControl): { [key: string]: any; } {
  return (typeof (control.value) !== "object") ?
    { "object": "O valor não é um objeto" } :
    null;
}

export function isValidObject(control: AbstractControl): { [key: string]: any; } {
  return ((typeof (control.value) !== "object") || Utils.isNullOrUndefined(control.value)) ? { "object": "Valor inválido" } : (control.value.id === 0) ?
    { "object": "O valor não é valido" } :
    null;
}

export function groupBy(xs, key) {
  return xs.reduce(function(rv, x) {
    (rv[x[key]] = rv[x[key]] || []).push(x);
    return rv;
  }, {});
};

// tslint:disable-next-line:max-line-length
export const emailPattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
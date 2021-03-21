import { HttpErrorResponse } from "@angular/common/http";

export function makeErrorResponse(response: HttpErrorResponse): any {
  if (response.status === 0) {
    return {
      "succes": false,
      "message":"Não foi possível alcançar o servidor de dados."
    };
  }

  return response.error;
}

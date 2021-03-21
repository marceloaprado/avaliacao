import { Marca } from "./marca";

export class Veiculo {
  id: number;
  veiculo: string;
  marca: Marca;
  ano: number;
  descricao: string;
  vendido: boolean;
  created: Date;
  updated: Date;
}

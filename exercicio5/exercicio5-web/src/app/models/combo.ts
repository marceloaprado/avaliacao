export class Combo {
    id: any;
    descricao: string;
    isTextOption?: boolean;

    constructor(id: number, descricao: string, isTextOption?: boolean) {
        this.id = id;
        this.descricao = descricao;
        this.isTextOption = isTextOption;
    }
}
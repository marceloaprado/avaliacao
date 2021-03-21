export interface MenuItem {
    texto: string;
    link: string;
    icone: string;
}

export interface OnMenuItemClick {
    onMenuItemClick(link: string): void;
}

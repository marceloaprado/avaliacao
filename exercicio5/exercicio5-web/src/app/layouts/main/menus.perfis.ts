import { MenuItem, OnMenuItemClick } from "../../utils/menu.utils";
import { Component, Input } from "@angular/core";
import { Router } from "@angular/router";

export const menus: MenuItem[] = [
    { texto: "Estatísticas", link: "estatisticas", icone: "analytics" },
    { texto: "Gerenciar", link: "gerenciar", icone: "space_dashboard" }
];

@Component({
    selector: "app-menu-item",
    templateUrl: "./menuitem.component.html",
    styleUrls: ["./main.component.scss"]
})
export class MenuItemComponent implements OnMenuItemClick {

    @Input() menuItem: MenuItem;

    constructor(
        public router: Router
    ) { }

    public onMenuItemClick(): void {               
        const moveTo = this.menuItem.link;

        this.router.navigate([moveTo]);        
    }
}


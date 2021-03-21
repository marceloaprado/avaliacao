import { Component, OnInit, OnDestroy, ChangeDetectorRef, ViewChild } from "@angular/core";

import { Router } from "@angular/router";
import { MenuItem } from "../../utils/menu.utils";
import { menus } from "./menus.perfis";
import { MediaMatcher } from '@angular/cdk/layout';

@Component({
  selector: "fate-main",
  templateUrl: "./main.component.html",
  styleUrls: ["./main.component.scss"]
})
export class MainComponent implements OnInit, OnDestroy {
  public title: string;
  public currentMenu: MenuItem[];
  mobileQuery: MediaQueryList;

  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, public router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnInit() {        
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  private loadMenu(url: string): void {
      this.currentMenu = menus;
  }

  public onMenuItemClick(url: string): void {
    this.loadMenu(url);
    this.title = url;
  }
}
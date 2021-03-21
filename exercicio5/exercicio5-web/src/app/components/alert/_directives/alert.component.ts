import { Component, OnInit, Input } from "@angular/core";

import { Alert, AlertType } from "../_models";
import { AlertService } from "../_services";
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
    animations: [
        trigger('fadeInOut', [
            state('void', style({
                opacity: 0
            })),
            transition('void <=> *', animate(500)),
        ])
    ],
    selector: "alert",
    templateUrl: "alert.component.html"
})

export class AlertComponent implements OnInit {
    @Input() id: string;

    alerts: Alert[] = [];

    constructor(private alertService: AlertService) { }

    ngOnInit() {
        this.alertService.getAlert(this.id).subscribe((alert: Alert) => {
            if (!alert.message) {
                // clear alerts when an empty alert is received
                this.alerts = [];
                return;
            }

            // add alert to array
            this.alerts.push(alert);
        });
    }

    removeAlert(alert: Alert) {
        this.alerts = this.alerts.filter(x => x !== alert);
    }

    copiaTexto(alert: Alert, event, componente: HTMLInputElement) {
        const range = document.createRange();
        range.selectNode(event.srcElement.lastChild);
        window.getSelection().removeAllRanges();
        window.getSelection().addRange(range);

        let copysuccess; // var to check whether execCommand successfully executed
        try {
            copysuccess = document.execCommand("copy");
        } catch (e) {
            copysuccess = false;
        }
    }

    cssClass(alert: Alert) {
        if (!alert) {
            return;
        }

        // return css class based on alert type
        switch (alert.type) {
            case AlertType.Success:
                return "alert alert-success fade";
            case AlertType.Error:
                return "alert alert-danger fade";
            case AlertType.Info:
                return "alert alert-info fade";
            case AlertType.Warning:
                return "alert alert-warning fade";
        }
    }
}

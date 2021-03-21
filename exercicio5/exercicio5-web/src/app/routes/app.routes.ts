import { MainComponent } from '../layouts/main/main.component';
import { Routes, RouterModule } from '@angular/router';
import { VeiculosComponent } from '../components/veiculos/veiculos.component';
import { EstatisticasComponent } from '../components/estatisticas/estatisticas.component';

const appRoutes: Routes = [
    {
        path: "", component: MainComponent, children: [        
            { path: "", component: EstatisticasComponent},
            { path: "gerenciar", component: VeiculosComponent},
            { path: "estatisticas", component: EstatisticasComponent}
        ]
    },    
    { path: "**", redirectTo: "estatisticas" }
];

export const AppRouting = RouterModule.forRoot(appRoutes);
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddShortenerComponent } from './add-shortener/add-shortener.component';
import { ShortenerComponent } from './shortener.component';

const routes: Routes = [
  { path: '', component: ShortenerComponent },
  { path: 'shortener', component: ShortenerComponent },
  { path: 'add', component: AddShortenerComponent },
  { path: '**', component: ShortenerComponent }
]
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShortenerRoutingModule { }

import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShortenerService } from 'src/app/shortener.service';

@Component({
  selector: 'app-add-shortener',
  templateUrl: './add-shortener.component.html',
  styleUrls: ['./add-shortener.component.scss']
})
export class AddShortenerComponent implements OnInit {

  /* This will be used as template driven form object to bind form data */
  model: any = {};
  constructor(private service: ShortenerService, private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void { 
    this.getQueryParams();
  }

  /**
   * This method will get the query params from route component if we have any
   */
  getQueryParams() {
    this.route.queryParams.subscribe(params => {
      if(params['longUrl']) {
        this.model.actualUrl = params['longUrl'];
        this.model.shortUrl = params['shortUrl'];
        this.model.id = params['id'];
        this.model.edate = params['expiredDate'] ? JSON.stringify(new Date(params['expiredDate'])).slice(1,11): undefined;
        this.model.cDate = params['createdDate'] ? JSON.stringify(new Date(params['createdDate'])).slice(1,11): undefined;
      }
    });
  }

  /**
   * This method will call save rest API with entered / modified data
   * Upon successful insertion, will redirect to dashboard page 
   */
  onSubmit() {
    const data: any = {
      longUrl: this.model.actualUrl
    }
    if(this.model.id) {
      data.id = +this.model.id;
    }
    if(this.model.shortUrl) {
      data.shortUrl = this.model.shortUrl;
    }
    if(this.model.edate) {
      data.expiredDate =  this.model.edate + 'T00:00:00.000Z';
    }
    if(this.model.cDate) {
      data.createdDate =  this.model.cDate + 'T00:00:00.000Z';
    }
    this.service.generateShortUrl(data).subscribe(res => {
        this.router.navigate(['/shortener']);
    }, error => console.log('error occured while trying to save / update Shortened Url ', error));
  }
}

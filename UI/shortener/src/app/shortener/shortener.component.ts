import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ShortenerService } from '../shortener.service';

@Component({
  selector: 'app-shortener',
  templateUrl: './shortener.component.html',
  styleUrls: ['./shortener.component.scss']
})
export class ShortenerComponent implements OnInit {

  urlsList: any[] = [];
  constructor(private route: Router, private service: ShortenerService) { }

  ngOnInit(): void { 
    this.getUrlsData();
  }

  /**
   * This method will get all the Shortened Urls data on component load
   */
  getUrlsData() {
    this.service.getAllUrls().subscribe((data: any[]) => {
      data.forEach(element => {
        element.createdDate = new Date(element.createdDate * 1000);
        element.expiredDate = new Date(element.expiredDate * 1000);
      });
      this.urlsList = data;
    });
  }

  /**
   * This will get the Actual Url from shorted Url by making Rest call
   * @param url will holds the short url value
   * 
   * P.S: Though we have Actual url in our table (just view), but actual requirement should implement below
   * 
   */
  redirectToActualUrl(url: any) {
    this.service.getActualUrl(url.shortUrl).subscribe(res => {
        window.open(res.longUrl, "_blank");
    }, error => console.error('Error occured while trying to get Long url ', error));
  }

  /**
   * This method will redirect to update page along with existing Url data
   * @param url holds the Url value
   */
  editUrl(url: any) {
    this.route.navigate(['/add'], {queryParams: {id: url.id, longUrl: url.longUrl, 
      shortUrl: url.shortUrl, expiredDate: url.expiredDate, createdDate: url.createdDate}});
  }

  /**
   * As name suggested will delete call Rest API to delete the Url entry
   * @param url holds the Url data
   */
  deleteUrl(url: any) {
    this.service.deleteActualUrl(url.id).subscribe(res => {
      this.getUrlsData();
    }, error => console.error('error occured while trying delete the Shortened Url ', error));
  }

  /**
   * Upon clicking on Add button, this method will route to Add / Update page
   */
  redirectToAddPage() {
    this.route.navigate(['/add']);
  }
}

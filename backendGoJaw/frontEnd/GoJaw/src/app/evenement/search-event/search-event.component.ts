import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { ActivatedRoute } from '@angular/router';
import { EventServiceService } from 'src/app/service/event-service/event-service.service';

@Component({
  selector: 'app-search-event',
  templateUrl: './search-event.component.html',
  styleUrls: ['./search-event.component.css']
})
export class SearchEventComponent implements OnInit {
  tabEvents:any;
  constructor(private eventService:EventServiceService,private actRoute: ActivatedRoute) {
    let gouv = this.actRoute.snapshot.paramMap.get('gouv');
    console.log(gouv);  
     this.eventService.geteventsByGouv(gouv).subscribe((data) => {
      this.tabEvents =data ;
      this.tabEvents.sort((a, b)=>{
        let dateA:any=new Date(a.date)
        let dateB:any=new Date(b.date)
        return dateA-dateB //sort by date ascending
      })
          })
      }
   ngOnInit() {
  }
}

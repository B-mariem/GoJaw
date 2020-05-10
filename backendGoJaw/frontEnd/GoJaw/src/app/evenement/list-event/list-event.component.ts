import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { EventServiceService } from 'src/app/service/event-service/event-service.service';

@Component({
  selector: 'app-list-event',
  templateUrl: './list-event.component.html',
  styleUrls: ['./list-event.component.css']
})
export class ListEventComponent implements OnInit {
tabEvents:any
tab:any
  constructor(private eventService:EventServiceService) {
    this.eventService.getEvents().subscribe((data)=>{
      this.tabEvents=data;
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

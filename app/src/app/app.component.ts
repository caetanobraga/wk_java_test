import { Component, ElementRef, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'wk-app';
  @ViewChild('fileInput') fileInput: any;
  @ViewChild('canvas1', { static: true }) elemento1: ElementRef;


  constructor(private http: HttpClient) {
    this.elemento1 = new ElementRef(null);
  }

  inputFileChange(event: any) {
    const file = event.target.files[0];
  if (file) {
    const formData = new FormData();
      formData.append('file', file);

      const apiUrl = 'http://localhost:8082/api/v1/candidates';

      this.http.post(apiUrl, formData)
      .subscribe(
        response => {
          console.log('Arquivo enviado com sucesso', response);
          this.getCandidatesByState();
        },
        error => {
          console.error('Erro ao enviar o arquivo', error);
        }
      );
    };
  }

  getCandidatesByState(){
    const apiUrl = 'http://localhost:8082/api/v1/candidates';

    this.http.get(apiUrl).subscribe({
      next: (response: any) => {
        this.processChartData(response);
      },
      error: error => {
        console.error('Erro ao obter os dados da API', error);
      }
    });
  }

  processChartData(data: { state: string, candidatesNumber: number }[]){
    console.log(data);

    const labels: string[] = data.map( item => item.state);

    const values: number[] = data.map(item => item.candidatesNumber);

    console.log(labels);
    console.log(values);

    const chartType = "bar";
    new Chart(this.elemento1.nativeElement, {
      type: chartType,
      data: {
        labels: labels,
        datasets:[
          {
            label: "Número de candidatos por UF",
            data: values,
          }
        ]
      },
    });
  }




}

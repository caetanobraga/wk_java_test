import { Component, ElementRef, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Chart from 'chart.js/auto';
import { ChartTypeRegistry } from 'chart.js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'wk-app';
  @ViewChild('fileInput') fileInput: any;
  @ViewChild('canvas1', { static: true }) element1: ElementRef;
  @ViewChild('canvas2', { static: true }) element2: ElementRef;
  @ViewChild('canvas3', { static: true }) element3: ElementRef;
  @ViewChild('canvas4', { static: true }) element4: ElementRef;
  @ViewChild('canvas5', { static: true }) element5: ElementRef;
  isLoading = false;
  

  constructor(private http: HttpClient) {
    this.element1 = new ElementRef(null);
    this.element2 = new ElementRef(null);
    this.element3 = new ElementRef(null);
    this.element4 = new ElementRef(null);
    this.element5 = new ElementRef(null);
  }

  inputFileChange(event: any) {
    const file = event.target.files[0];
  if (file) {
    const formData = new FormData();
      formData.append('file', file);

      const apiUrl = 'http://localhost:8082/api/v1/candidates';
      this.isLoading = true;

      this.http.post(apiUrl, formData)
      .subscribe(
        response => {
          console.log('Arquivo enviado com sucesso', response);
          this.getCandidatesByState();
          this.getImcByAgeGroup();
          this.getObesePercent();
          this.getAgeMediaByBloodType();
          this.getPossibleDonorsByBloodType();
          this.isLoading = false;
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
        this.processChartCandidatesByStateData(response);
      },
      error: error => {
        console.error('Erro ao obter os dados da API', error);
      }
    });
  }

  getObesePercent(){
    const apiUrl = 'http://localhost:8082/api/v1/obeses-percentage';

    this.http.get(apiUrl).subscribe({
      next: (response: any) => {
        this.processChartObesePercent(response);
      },
      error: error => {
        console.error('Erro ao obter os dados da API', error);
      }
    });
  }

  getImcByAgeGroup(){
    const apiUrl = 'http://localhost:8082/api/v1/media-imc';

    this.http.get(apiUrl).subscribe({
      next: (response: any) => {
        this.processChartImcByAgeGroup(response);
      },
      error: error => {
        console.error('Erro ao obter os dados da API', error);
      }
    });
  }

  getAgeMediaByBloodType(){
    const apiUrl = 'http://localhost:8082/api/v1/average-bloodtype';

    this.http.get(apiUrl).subscribe({
      next: (response: any) => {
        this.processChartAgeMediaByBloodType(response);
      },
      error: error => {
        console.error('Erro ao obter os dados da API', error);
      }
    });
  }

  getPossibleDonorsByBloodType(){
    const apiUrl = 'http://localhost:8082/api/v1/donors-by-bloodtype';

    this.http.get(apiUrl).subscribe({
      next: (response: any) => {
        this.processChartPossibleDonorsByBloodType(response);
      },
      error: error => {
        console.error('Erro ao obter os dados da API', error);
      }
    });
  }

  chartCandidatesByStateData: Chart | null = null;

  processChartCandidatesByStateData(data: { state: string, candidatesNumber: number }[]){

    const labels: string[] = data.map( item => item.state);

    const values: number[] = data.map(item => item.candidatesNumber);
    
    if (this.chartCandidatesByStateData){
      this.chartCandidatesByStateData.destroy();
    }

    const chartType: keyof ChartTypeRegistry = 'bar';
    const config = {
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
    }
    this.chartCandidatesByStateData = new Chart(this.element1.nativeElement, config)
  }

  chartImcByAgeGroup: Chart | null = null;

  processChartImcByAgeGroup(data: { ageGroup: number, imcMedia: number }[]){

    const labels: string[] = data.map( item => item.ageGroup+1 + " - " + (item.ageGroup+10));

    const values: number[] = data.map(item => item.imcMedia);

    if (this.chartImcByAgeGroup){
      this.chartImcByAgeGroup.destroy();
    }

    const chartType: keyof ChartTypeRegistry = 'bar';
    const config = {
      type: chartType,
      data: {
        labels: labels,
        datasets:[
          {
            label: "Imc médio por faixa etária",
            data: values,
          }
        ]
      },
    }
    this.chartImcByAgeGroup = new Chart(this.element2.nativeElement, config);
  }

  chartObesePercent: Chart | null = null;

  processChartObesePercent(data: { sex: string, percent: number }[]){
  const labels: string[] = data.map(item => item.sex);
  const values: number[] = data.map(item => item.percent);

  if (this.chartObesePercent) {
    this.chartObesePercent.destroy(); 
  }

  const chartType: keyof ChartTypeRegistry = 'bar';
  const config = {
    type: chartType,
    data: {
      labels: labels,
      datasets: [
        {
          label: 'Percentual de obesos entre gêneros',
          data: values,
        }
      ]
    },
  };

  this.chartObesePercent = new Chart(this.element3.nativeElement, config);
  }

  chartAgeMediaByBloodType: Chart | null = null;

  processChartAgeMediaByBloodType(data: { bloodType: string, averageAge: number }[]){

    const labels: string[] = data.map( item => item.bloodType);

    const values: number[] = data.map(item => item.averageAge);

    if (this.chartAgeMediaByBloodType){
      this.chartAgeMediaByBloodType.destroy();
    }

    const chartType: keyof ChartTypeRegistry = 'bar';
    const config = {
      type: chartType,
      data: {
        labels: labels,
        datasets:[
          {
            label: "Média de idade entre candidatos à doação",
            data: values,
          }
        ]
      },
    }
    this.chartAgeMediaByBloodType = new Chart(this.element4.nativeElement, config);
  }

  chartPossibleDonorsByBloodType: Chart | null = null;

  processChartPossibleDonorsByBloodType(data: { bloodType: string, donorCount: number }[]){

    const labels: string[] = data.map( item => item.bloodType);

    const values: number[] = data.map(item => item.donorCount);

    if (this.chartPossibleDonorsByBloodType){
      this.chartPossibleDonorsByBloodType.destroy();
    }

    const chartType: keyof ChartTypeRegistry = 'bar';
    const config =  {
      type: chartType,
      data: {
        labels: labels,
        datasets:[
          {
            label: "Possíveis doadores para cada tipo sangüíneo.",
            data: values,
          }
        ]
      },
    }
    this.chartPossibleDonorsByBloodType = new Chart(this.element5.nativeElement, config);
  }
}

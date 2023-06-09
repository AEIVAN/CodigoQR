import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { QrDocumentGeneral } from '../modelo/QrDocumentGeneral';
import { ResponseDTO } from '../modelo/ResponseDTO';
import { environment } from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {

  headers = new HttpHeaders().append("accept","*/*")
  .append('Content-Type','application/json')
  .append('Access-Control-Allow-Origin', '*')
  .append('Access-Control-Allow-Credentials', 'true')
  .append('Access-Control-Allow-Headers', 'Content-Type')
  .append('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');

  constructor(private http: HttpClient) { }
  //baseURL: string = "http://localhost:8081/file/";
  //baseURL: string = "http://192.168.0.16:8080/qr/file/";
  //baseURL: string = "http://192.168.2.114:8080/qr/file/";
  //baseURL: string = "http://localhost:8080/qr/file/"; 
  //baseURL: string = "http://apideisa.hopto.org:8080/qr/file/"; 
  baseURL: string =  environment._baseURL;    //"http://52.23.232.199:8080/qr/file/"; 
  //baseURL: string = "http://localhost:8080/file/";
 
  downLoadDocument(id:String) {
    console.log("getInformation " + id);
    return this.http.get(this.baseURL + '/qr/file/downLoadDocument?id='+id, { responseType : 'blob' })
  }

  getQrDocumentGeneralId(id:String): Observable<QrDocumentGeneral> {
    console.log("getInformation " + id);
    return this.http.post<QrDocumentGeneral>(this.baseURL+'/qr/file/getQrDocumentGeneralId?id='+id, {headers:this.headers})
  }
  validaContrasenia(id:String,contrasenia:String):Observable<ResponseDTO> {
    console.log("getInformation " + id);
    console.log("contrasenia " + contrasenia);
    const headerss = { 'content-type': 'application/json'}  
    //return this.http.get(this.baseURL + 'test?tes='+id, {'headers':headers})
    return this.http.post<ResponseDTO>(this.baseURL + '/qr/file/getValidaContrasenia?id='+id+'&contrasenia='+contrasenia, {headers:headerss})
  }
}

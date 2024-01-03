import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Documento } from './modelo/Documento';
import { saveAs } from 'file-saver';
import { QrDocumentGeneral } from './modelo/QrDocumentGeneral';
import { QrDocumentSelected } from './modelo/QrDocumentSelected';
import { ApiServiceService } from './servicio/api-service.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ResponseDTO } from './modelo/ResponseDTO';
import { EncuestaComponent } from './encuesta/encuesta.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @ViewChild('ModalClose') document: ElementRef;

  //private apiService : ApiServiceService ; 
  private qrDocumentGeneral: QrDocumentGeneral;
  public qrDocumentSelectedList: QrDocumentSelected[] = [];
  public qrDocumentSelected: QrDocumentSelected;
  public contraseniaFormGroup : FormGroup ; 
  public data : ResponseDTO ; 

  public id = "";
  public razonSocial!: String;
  public numero!: String;
  public tipo = "";
  public descargaDocumento !: String;
  public descargaId !: String;
  public idSeleccionado !: String;
  public result = false;
  public descargaError = false;
  public descargaFalta = false;
  public errorContrasenia : String =""; 
  public contrasenia : Boolean = false; 
  public flagSpinner = false ; 
  public flagDescarga = false ; 
  public isVisible: boolean = false;

  columnas: string[] = ['nombre', 'tipo', 'fecha','qr'];
  imagen1: String ="assets/Normal/"; 
  imagen2: String ="assets/Normal/"; 

  sabias1: String ="assets/Sabias_que/"; 
  sabias2: String ="assets/Sabias_que/"; 




  constructor(
    private _formBuilder : FormBuilder,
    private _route: ActivatedRoute,
    private apiService: ApiServiceService,
    private modalService: NgbModal
  ) {
  }
  ngOnInit(): void {

    this.result = false ; 
    this.flagSpinner = true ; 

    if (this._route != null)
      this._route.queryParams.subscribe(params => {
        this.id = params['id'];
        if (this.id) {
          this.cargaInformacion(this.id);
        }
      });
    this.contraseniaFormGroup = this._formBuilder.group({
      contrasenia : new FormControl(null,[ Validators.required ,Validators.minLength(4)])
    }); 

    this.tipo = "";
    this.descargaDocumento = "";
    this.descargaId = "";

    let min = 1;
    let max = 33;
    //this.imagen1 = this.imagen1+ "Imagen" + (Math.floor(Math.random() * (max - min + 1)) + min) + ".jpg" ; 
    //this.imagen2 = this.imagen2 +"Imagen" + (Math.floor(Math.random() * (max - min + 1)) + min) + ".jpg" ; 
    this.imagen1 = this.imagen1+ "Imagen0001" + ".jpg" ; 
    this.imagen2 = this.imagen2+ "Imagen0002" + ".jpg" ; 
    min = 1 ; 
    max = 9 ; 
    //this.sabias1 = this.sabias1+ "Imagen" + (Math.floor(Math.random() * (max - min + 1)) + min) + ".jpg" ; 
    //this.sabias2 = this.sabias2 +"Imagen" + (Math.floor(Math.random() * (max - min + 1)) + min) + ".jpg" ; 
    this.sabias1 = this.sabias1+ "Imagen001" + ".jpg" ; 
    this.sabias2 = this.sabias2+ "Imagen002" + ".jpg" ; 
    console.log("---------------------->" + this.sabias1);
    this.mostrarPopup();
  }

  mostrarPopup() {
    // Abre un modal
    const modalRef = this.modalService.open(EncuestaComponent);

  }

  onSubmit(): void {


  }

  
  seleccionar(documento: Documento) {
    if (documento.extension == "pdf") {
      this.tipo = "pdf";
    }
    else if (documento.extension == "docx") {
      this.tipo = "word";
    }
    else if (documento.extension == "xlsx") {
      this.tipo = "excel";
    }
    else if (documento.extension == "jpg") {
      this.tipo = "imagen";
    }

    this.descargaDocumento = documento.nombre + "." + documento.extension;
    this.descargaId = documento.id;

  }

  descargarId(id: String, nombre:String, extension: String) {
    this.idSeleccionado = id ; 
    this.descargaDocumento = nombre + "." + extension;
    this.errorContrasenia = ""; 
    this.contrasenia = false ; 
    this.contraseniaFormGroup.controls['contrasenia'].setValue("");
    return ; 
  }

  validaContrasenia(){
    console.log("Holassss");
    this.errorContrasenia = ""; 
    this.contrasenia = false ; 
    if(this.contraseniaFormGroup.invalid){
      this.contraseniaFormGroup.markAllAsTouched();
      this.errorContrasenia = "La contraseña no es valida";
      return
    }
    this.flagDescarga = true ; 
    this.apiService.validaContrasenia(this.idSeleccionado, this.contraseniaFormGroup.controls['contrasenia'].value.toString(),"WEB Cliente",this.numero).subscribe(
      data => {
        if (data.body) {
          if (data.body.toString().includes("Valido")){
            this.contrasenia = true ; 
            //console.log(this.exampleModalCenter.nativeElement);
            this.descargar(this.idSeleccionado); 
          }
          else if (data.body.toString().includes("Invalido")){
            this.errorContrasenia = "La contraseña no es valida"; 
            this.contraseniaFormGroup.controls['contrasenia'].setValue("");
            this.contraseniaFormGroup.markAllAsTouched();
            this.flagDescarga = false ; 
          }else if (data.body.toString().includes("Descargas")){
            this.errorContrasenia = "No se tienen descargas disponibles"; 
            this.contraseniaFormGroup.controls['contrasenia'].setValue("");
            this.contraseniaFormGroup.markAllAsTouched();
            this.flagDescarga = false ; 
          }
        }
        else{
          this.errorContrasenia = "No se pudo validar la contraseña"; 
          this.flagDescarga = false ; 
        }

      },
      err => {
        this.errorContrasenia = "Error al validar la contraseña"; 
        this.flagDescarga = false ; 
        console.error(err);
      }
    )
  }

  descargar(id : String ) {
    this.descargaFalta = false;
    this.descargaError = false ;
    let mediaType = 'application/pdf';
    console.log("descargar " + this.id); // Print the parameter to the console. 
    this.apiService.downLoadDocument(this.idSeleccionado).subscribe(
      data => {
        if (data && data.size != 0 && (this.descargaDocumento +"").includes('zip')) {
          var a = document.createElement('a');
          var blob = new Blob([data], {'type':"application/octet-stream"});
          a.href = URL.createObjectURL(blob);
          //a.download =  "filename.zip";
          console.log("this.descargaDocumento |" + this.descargaDocumento + "|");
          a.download = this.descargaDocumento +"";
          a.click(); 
          this.flagDescarga = false ; 
          document.getElementById("ModalClose")?.click();
        }
        else if (data && data.size != 0 && (this.descargaDocumento +"").includes('pdf') ) {
          let blob = new Blob([data as Blob], { type: mediaType });
          console.log("descargar 2 " + this.id);
          saveAs(blob, this.descargaDocumento + "");
          this.flagDescarga = false ; 
          document.getElementById("ModalClose")?.click();
        }
        else{
          this.descargaFalta = true;
          this.flagDescarga = false ; 
          document.getElementById("ModalClose")?.click();
        }

      },
      err => {
        this.descargaError = true ;
        console.error(err)
      }
    )

  }

  cargaInformacion(id: String) {
    let flag = false ; 
    console.log("this.id " + this.id); // Print the parameter to the console. 
    this.apiService.getQrDocumentGeneralId(id).subscribe(
      data => {
        this.qrDocumentGeneral = data.body;
        this.qrDocumentSelectedList = [];
        this.qrDocumentGeneral.qrDocumentosInfo.forEach(element => {
          this.qrDocumentSelected = new QrDocumentSelected();
          if (element.id == id) {
            this.razonSocial = element.razonSocial;
            this.numero = element.numero;
            this.qrDocumentSelected.checked = true;
            flag = true ; 
          }
          else {
            this.qrDocumentSelected.checked = false;
          }
          this.qrDocumentSelected.id = element.id;
          this.qrDocumentSelected.departamento = element.departamento;
          this.qrDocumentSelected.documento = element.documento;
          this.qrDocumentSelected.numero = element.numero;
          this.qrDocumentSelected.razonSocial = element.razonSocial;
          this.qrDocumentSelected.nombre = element.nombre;
          this.qrDocumentSelected.extension = element.extension;
          this.qrDocumentSelected.qr = element.qr;
          this.qrDocumentSelected.file = element.file;
          this.qrDocumentSelected.url = element.url;
          this.qrDocumentSelected.estado = element.estado;
          this.qrDocumentSelected.date = element.fecha; 
          this.qrDocumentSelectedList.push(this.qrDocumentSelected);
          this.isVisible = true ; 
          //this.result = true;
          //setTimeout(() => {  this.flagSpinner = false ;  }, 1000); 
        });
        if (flag){
          setTimeout(() => {  this.result = true; this.flagSpinner = false ; }, 500); 
        }
        else{
          setTimeout(() => {  this.result = false; this.flagSpinner = false ; }, 500); 
        }
      },
      err => {
        console.error(err); 
        setTimeout(() => {  this.flagSpinner = false ;  }, 1000);
      },
      ()=>{
        
      }
      
    )
  }

  

  checkAllCheckBox(ev: any) { // Angular 13
    this.qrDocumentSelectedList.forEach(x => x.checked = ev.target.checked)
  }

  isAllCheckBoxChecked() {
    return this.qrDocumentSelectedList.every(p => p.checked);
  }

  getErrorMessage(){
    return this.errorContrasenia;
  }



}

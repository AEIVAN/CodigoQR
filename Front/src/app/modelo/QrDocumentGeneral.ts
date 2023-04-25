import { QrDocument } from "./QrDocument";

export class QrDocumentGeneral{
    id : String ;
	departamento : String;
	documento :String ;
	numero : String ;
	qrDocumentosInfo !: QrDocument[];

}
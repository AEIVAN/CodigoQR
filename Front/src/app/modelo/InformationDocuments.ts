import {Documento} from './Documento';

export class InformationDocuments{
    cliente ?: String; 
    dirigido ?: String; 
    emision ?: String;  
    departamento ?: String; 
    documento ?: String; 
    numero ?: String; 
    nombre ?: String; 
    id ?: String; 
    documentos!: Documento[];
}
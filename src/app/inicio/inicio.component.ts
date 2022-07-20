import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Postagem } from '../model/Postagem';
import { Tema } from '../model/Tema';
import { Usuario } from '../model/Usuario';
import { AuthService } from '../service/auth.service';
import { PostagemService } from '../service/postagem.service';
import { TemaService } from '../service/tema.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  postagem: Postagem = new Postagem()
  listaPostagem: Postagem[]
  listaTemas: Tema[]
  idTema: number
  tema: Tema = new Tema()

  usuario: Usuario = new Usuario()
  idusuario = environment.id

  constructor(
    private router: Router,
    private postagemService: PostagemService,
    private temaService:TemaService,
    private authService:AuthService
  ) { }

  ngOnInit() {

    if(environment.token ==''){
      alert('Sua sessão expirou')
      this.router.navigate(['/entrar'])
    }

    this.getAllTemas()
    this.getAllPostagens()
    this.findByIdUsuario()
  }


  findByIdUsuario(){
    this.authService.getByIdUsuario(this.idusuario).subscribe((resp: Usuario)=>{
      this.usuario= resp
      
    })
  }


  getAllTemas(){

    this.temaService.getAllTema().subscribe((resp:Tema[])=>{
      this.listaTemas = resp
    })
    
  }

  findByIdTema(){
    this.temaService.getByIdTema(this.idTema).subscribe((resp:Tema)=>{
      this.tema = resp
    })
  }

  findTemaID(id: number){
    this.temaService.getByIdTema(id).subscribe((resp:Tema)=>{
      this.tema = resp
    })
  }

  getAllPostagens(){
    this.postagemService.getAllPostagens().subscribe((resp:Postagem[])=>{
      this.listaPostagem = resp
    })
  }

  publicar(){

    this.tema.id = this.idTema
    this.postagem.tema = this.tema

    this.usuario.id = this.idusuario
    this.postagem.usuario = this.usuario

    this.postagemService.postPostagem(this.postagem).subscribe((resp:Postagem)=>{
      this.postagem = resp
      alert('Postagem realizada com sucesso')
      this.postagem = new Postagem()
      this.getAllPostagens()
    })
  }

  apagar(id:number) {
    this.postagemService.deletePostagem(id).subscribe(()=> {
      alert('Postagem excluida com sucesso!')
      this.router.navigate(['/inicio'])
    })
    this.getAllPostagens()
    this.getAllTemas()

  }

}

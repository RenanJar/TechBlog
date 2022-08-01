import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../model/Usuario';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {

  usuario: Usuario = new Usuario()
  confirmSenha: String
  tipoUsuario: String

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(){

    window.scroll(0,0)

  }

  confirmarSenha(event:any){

    this.confirmSenha = event.target.value

  }

  cadastrar(){

    if(this.confirmSenha.length < 8){
      alert('Senha pequena, minimo de 8 digitos!')
    }

    if(this.usuario.usuario == ""||this.usuario.nome ==""){
      alert('Campo Nome ou Usuario inválidos')
    }

    if(this.usuario.senha != this.confirmSenha){
      alert('senhas divergentes')
    }else{
      this.authService
      .cadastrar(this.usuario)
      .subscribe((resp: Usuario)=>{

        this.usuario = resp
        this.router.navigate(['/entrar'])
        alert('usúario cadastrado com sucesso ! ! !')

      })
    }
  }


}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioLogin } from '../model/UsuarioLogin';
import { AuthService } from '../service/auth.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-entrar',
  templateUrl: './entrar.component.html',
  styleUrls: ['./entrar.component.css']
})
export class EntrarComponent implements OnInit {

  usuarioLogin: UsuarioLogin = new UsuarioLogin()


  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(){
    if(environment.token != ''){
      environment.token=''
    }
    this.alertParaRecruiters()
    window.scroll(0,0)
  }

  alertParaRecruiters(){
   alert('Seja bem vindo(a) ao projeto TechBlog. O presente projeto é hospedado em um servidor gratuito,' 
   +'sendo assim, ele pode demorar um pouquinho para responder as requisições,'+ 
   ' caso não queira se cadastrar, aqui vai um login para testes!!!  login: teste@gmail.com senha: 123456789 Obrigado!')
  }

  entrar(){
    this.auth.entrar(this.usuarioLogin).subscribe((resp:UsuarioLogin)=>{
      this.usuarioLogin = resp;

      environment.token = this.usuarioLogin.token;
      environment.nome = this.usuarioLogin.nome;
      environment.foto = this.usuarioLogin.foto;
      environment.id = this.usuarioLogin.id;

      console.log(environment.id)


      this.router.navigate(['/inicio'])


    }, erro =>{
      if(erro.status == 500 || erro.status == 401){
        alert('Usuario ou senhas inválidos')
      }
    })
  }

}

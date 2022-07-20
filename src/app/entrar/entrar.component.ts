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
    window.scroll(0,0)
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

# E-Event

Projeto que mostra eventos onde o usuário pode ver a descrição, data, valor e local do evento, além de poder fazer um check-in de comparecimento.

# Como usar?

Primeiro, abra seu git bash ou terminal preferido, depois disso, execute este comando:

```bash
git clone https://github.com/matheus-kittler/E-Event.git
```


Agora com o projeto em sua máquina, seria interessante utilizar o Android Studio para ver os fonts e também para emular o app.
Após abrir o Android Studio vá em File > Open... > pasta onde você clonou o app quando utilizou o git bash ou terminal.

caso não saiba executar um app:


[Executar em um dispositivo real](https://developer.android.com/training/basics/firstapp/running-app?hl=pt-br)

e

[Executar um app no Android Emulator](https://developer.android.com/studio/run/emulator?hl=pt-br)

*OBS*: Cuidado com o ip de sua máquina para rodar o app no emulador, se estiver apontando para alguma VPN empresarial o app não conseguirá consumir a API!

# Arquitetura e Frameworks

Projeto com arquitetura MVVM onde estão sendo utilizando as bibliotecas Retrofit, Koin para injeção de dependência, Glide e Fragmets Extensions.

O retrofit é utilizado primeiramente por ser mais simples a implementação do que o AsyncTask, além de por ser fácil de recuperar e fazer upload.
Também é a forma que aprendi mais efetiva durante meu estágio de consumir e dados JSON.

Utilizei o Koin para testar minhas habilidades e também trabalhar com injeção de dependência, algo que não faço ultimamente, além de melhorar meu código.

Já no caso do Glide, utilizei ele por ser a opção que baixa mais rápido as imagens na rede comparado ao Picasso, por exemplo.
Glide foi o que mais tive contato quando estava tratando com imagens em projetos no meu estágio.

Fragments Extensions é justamente para fornecer extensões Kotlin para APIs de framework comuns e várias extensões específicas do domínio.

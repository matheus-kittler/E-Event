# E-Event

Projeto que mostra eventos onde o usuário pode ver a descrição, data, valor e local do evento, além de poder fazer um check-in de comparecimento.

# Como usar?

Primeiro, abra seu git bash ou terminal preferido, depois disso, execute este comando:

```bash
git clone:
```

Agora com o projeto em sua máquina, seria interessante utilizar o Android Studios para ver os fonts e também para emular o app.

# Arquitetura e Frameworks

Projeto com arquitetura MVVM onde estão sendo utilizando as bibliotecas Retrofit, Glide e Fragmets Extensions.

O retrofit é utilizado primeiramente por ser mais simples a implementação do que o AsyncTask, além de por ser fácil de recuperar e fazer upload.
Também é a forma que aprendi mais efetiva durante meu estágio de consumir e dados JSON.

Já no caso do Glide, utilizei ele por ser a opção que baixa mais rápido as imagens na rede comparado ao Picasso, por exemplo.
Glide foi o que mais tive contato quando estava tratando com imagens em projetos no meu estágio.

Fragments Extensions é justamente para fornecer extensões Kotlin para APIs de framework comuns e várias extensões específicas do domínio.

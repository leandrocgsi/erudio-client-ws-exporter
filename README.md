Erudio Client WS Exporter
=========================

Este é um cliente web que se comunica com a aplicação Erudio servidor via RMI e disponibiliza web-services para o Cliente Android e para aplicações em outras linguagens.


![Estrutura de Projetos e Pacotes do Server](https://github.com/leandrocgsi/erudio/blob/master/img/image7.png?raw=true)

Agora será abordado o cliente **WS-Exporter**, ele é o responsável por intermediar o acesso de aplicações desenvolvidas em linguagens diferentes de Java e mobile. O papel desse cliente é disponibilizar os web-serviçes produzidos pelo servidor. Isto se deve ao fato de o Server não estar sob um contêiner web logo os web-services não estão sob o protocolo HTTP. 
Como se sabe para que os web-services sejam disponibilizados é fundamental que eles possam ser acessados pelo protocolo HTTP, daí a necessidade de se criar outra aplicação que esteja sob um contêiner web. Sendo assim podemos dizer que o Server produz o JSON ou XML e o consome, mas quem efetivamente os disponibiliza é o **WS-Exporter** visto que ele coloca-os sob o **protocolo HTTP**.
Um ponto importante a se destacar no **WS-Exporter** é que, como a segurança é feita pelo **Spring Security 3.1**, para que se tenha acesso aos web-services, é necessário que o usuário informe suas credenciais durante a requisição. Por padrão o **WS-Exporter** suporta apenas a codificação **Base64** e não permite a criação de uma sessão por usuário. Desse modo é necessário enviar as credenciais a cada nova requisição.
Conforme já foi dito, anteriormente, o WS-Exporter invoca remotamente métodos do pacote Utils do Server. Ao observar a imagem acima se pode perceber que as implementações do Utils são relativamente simples, basicamente tratam-se de Beans e Interfaces.  A estrutura do **WS-Exporter** por sua vez também é relativamente simples tendo uma factory na qual os serviços RMI serão injetados, uma classe correspondente a cada serviço a ser disponibilizado que implementa a interface REST do utils correspondente e duas classes responsáveis por prover segurança a aplicação (**CustomAuthenticationProvider** e **RESTAuthenticationEntryPoint**). Além disso temos apenas arquivos de configuração do Spring e o web.xml.

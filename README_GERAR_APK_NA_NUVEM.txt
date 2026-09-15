CONTROLE DE ESTOQUE MOBILE V5.6 — GERAR APK SEM ANDROID STUDIO

Este projeto é um aplicativo Android simples que abre a versão mobile do seu Controle de Estoque em um WebView.

IMPORTANTE:
- O APK não contém o servidor Flask nem o banco SQLite.
- Ele precisa de um endereço do sistema V5.6 funcionando (internet ou rede local).
- Na primeira abertura, o aplicativo pede o endereço do servidor.
- Não inclui scanner nem código de barras.

FORMA MAIS FÁCIL DE GERAR O APK:
1. Crie um repositório PRIVADO no GitHub.
2. Envie todos os arquivos desta pasta para o repositório.
3. Vá em Actions no GitHub.
4. O workflow "Gerar APK Android" será executado.
5. Abra a execução concluída e baixe o artefato "controle-estoque-apk".
6. Dentro do ZIP estará o app-debug.apk.
7. Instale o APK no celular.

Ao abrir o APK, informe o endereço do seu sistema, por exemplo:
https://seu-endereco-do-servidor

Quando o sistema for publicado, esse endereço será o endereço definitivo.

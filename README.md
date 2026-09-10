# PC Parts Manager — projeto completo + Docker

Este zip já é um projeto Android/Gradle pronto (não é só os `.kt` soltos),
com um `docker-compose.yml` para compilar o APK sem precisar instalar
Android Studio na máquina — só Docker.

## Compilar com Docker Compose

```bash
docker compose up --build
```

Isso vai:
1. Montar uma imagem com JDK 17, Gradle e o Android SDK (baixa umas
   coisas na primeira vez, pode demorar alguns minutos).
2. Rodar `gradle assembleDebug` dentro do container.
3. Gerar o APK em `app/build/outputs/apk/debug/app-debug.apk`, na pasta
   do projeto na sua máquina (o volume `.:/app` sincroniza isso).

Depois é só instalar esse `.apk` num emulador ou celular Android
(`adb install app-debug.apk`), ou abrir o projeto inteiro no Android
Studio normalmente — o Docker é opcional, só uma forma alternativa de
compilar sem abrir a IDE.

## Se preferir abrir no Android Studio

Abram a pasta `PCPartsManager` (a raiz deste zip) como projeto no Android
Studio. Ele já tem `settings.gradle.kts`, `build.gradle.kts` e o `app/`
configurados — não precisa colar nada em outro projeto.

## Estrutura

```
PCPartsManager/
├── docker-compose.yml
├── Dockerfile
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── res/values/ (strings.xml, themes.xml)
│       └── java/com/example/myapplication/
│           ├── MainActivity.kt        (integração / navegação)
│           ├── Modelos.kt             (data classes)
│           ├── Dados.kt               (dados de exemplo)
│           ├── Componentes.kt         (UI reaproveitada)
│           ├── TelaInicio.kt          (Integrante 1)
│           ├── TelaInventario.kt      (Integrante 2)
│           ├── TelaComputadores.kt    (Integrante 3)
│           ├── TelaDesejos.kt         (Integrante 4)
│           ├── TelaDetalhePeca.kt     (Integrante 5)
│           └── ui/theme/              (Color.kt, Theme.kt, Type.kt)
└── LEIA-ME.md   (detalhes sobre a divisão de telas e as restrições da atividade)
```

Detalhes sobre divisão do trabalho em equipe e sobre quais conceitos de
aula foram usados estão no `LEIA-ME.md`.

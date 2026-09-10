# PC Parts Manager — Etapa 2 (Implementação)

## O que tem aqui

7 arquivos Kotlin prontos para colar dentro do projeto Android que vocês já têm
(o mesmo padrão do `myapplication` visto em aula, com `ui/theme/`):

| Arquivo | Conteúdo | Responsável |
|---|---|---|
| `Modelos.kt` | `data class` de Peça, Computador, Desejo, etc. | (compartilhado) |
| `Dados.kt` | Listas de exemplo (substitui um banco de dados) | (compartilhado) |
| `Componentes.kt` | Pedaços de UI reaproveitados (cartão de peça, badge de status, chip de filtro) | (compartilhado) |
| `TelaInicio.kt` | Tela 1 — dashboard | **Integrante 1** |
| `TelaInventario.kt` | Tela 2 — lista de peças com busca/filtro | **Integrante 2** |
| `TelaComputadores.kt` | Tela 3 — montagens (builds) | **Integrante 3** |
| `TelaDesejos.kt` | Tela 4 — lista de desejos | **Integrante 4** |
| `TelaDetalhePeca.kt` | Tela 5 — detalhe de uma peça | **Integrante 5** |
| `MainActivity.kt` | Junta tudo e faz a navegação | quem ficar de integrador(a) |

Como o grupo tem 5+ pessoas e o mínimo é 3 telas, dá pra:
- 5 pessoas ficarem com uma tela cada (o que já está pronto), e
- quem sobrar cuidar do `Modelos.kt`/`Dados.kt` (modelar os dados) e do
  `MainActivity.kt` (integração), ou adicionar uma funcionalidade extra em
  cima de uma tela existente (ex.: um botão "editar" na tela de detalhe).

Na apresentação, cada um explica o arquivo/tela que fez.

## Como instalar

1. Abram o projeto Android que já têm (aquele com `com.example.myapplication`).
2. Copiem os 7 arquivos `.kt` para dentro de
   `app/src/main/java/com/example/myapplication/`.
3. **Atenção**: já existe um `MainActivity.kt` no projeto de vocês (o das aulas
   de Compose, com `ListaLazyColumn`/`Formulario`). Substituam o conteúdo dele
   pelo `MainActivity.kt` novo (ou apaguem o antigo e copiem este no lugar).
4. Rodem o app. A barra de baixo troca entre Início / Peças / PCs / Desejos, e
   tocar em qualquer peça abre a tela de detalhe.

Se o pacote do projeto de vocês não for `com.example.myapplication`, troquem
a primeira linha (`package com.example.myapplication`) de todos os arquivos
para o nome do pacote correto (Android Studio faz isso automaticamente se
vocês usarem "Refactor > Rename" ou simplesmente arrastarem os arquivos para
dentro do pacote certo).

## Sobre as restrições da atividade

Usei só o que apareceu no material de aula de vocês:
- `remember` / `mutableStateOf` para estado (igual ao `Form.kt`)
- `LazyColumn` com `items { }` (igual ao `MainActivity.kt` da aula de listas)
- `OutlinedTextField`, `Card`, `Row`/`Column`, `Modifier.clickable`
- `data class`, `when`, `filter`/`forEach`/`take`, funções com parâmetros
  do tipo função (lambdas), exatamente como na aula de funções
- `Scaffold` com `bottomBar` — é o mesmo `Scaffold` que vocês já usam, só
  usando mais um dos parâmetros dele

**Não usei**: Navigation Compose, ViewModel, Room/banco de dados, coroutines,
bibliotecas de gráfico. A navegação é só uma variável de texto (`telaAtual`)
trocada com `when`, do jeito mais simples possível.

Se o professor não tiver visto `horizontalScroll` (usado no filtro de
categorias da tela de Inventário) ou `Modifier.background` com cor
(usado nos badges), é só avisar que eu ajusto para uma versão ainda mais
simples — nenhum desses dois é essencial para o funcionamento do app.

## O que foi simplificado em relação ao mockup HTML

O design original que vocês me mandaram tinha mais coisas do que o pedido
mínimo (gráfico de pizza, alertas de garantia, tela "Mais", modal de
cadastro). Deixei de fora o que dependia de recursos mais avançados
(desenho de gráfico customizado, por exemplo) e mantive a essência de cada
tela com componentes básicos do Compose.

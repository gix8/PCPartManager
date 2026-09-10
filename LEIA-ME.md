# PC Parts Manager — Etapa 2 (Implementação)

## O que tem aqui

7 arquivos Kotlin prontos para colar dentro do projeto Android que já têm
(o mesmo padrão do `myapplication` visto em aula, com `ui/theme/`):

| Arquivo | Conteúdo | Responsável                 |
|---|---|-----------------------------|
| `Modelos.kt` | `data class` de Peça, Computador, Desejo, etc. | (compartilhado)             |
| `Dados.kt` | Listas de exemplo (substitui um banco de dados) | (compartilhado)             |
| `Componentes.kt` | Pedaços de UI reaproveitados (cartão de peça, badge de status, chip de filtro) | (compartilhado)             |
| `TelaInicio.kt` | Tela 1 — dashboard | **Bernardo**                |
| `TelaInventario.kt` | Tela 2 — lista de peças com busca/filtro | **Giovani X**               |
| `TelaComputadores.kt` | Tela 3 — montagens (builds) | **Vinicius**                |
| `TelaDesejos.kt` | Tela 4 — lista de desejos | **João**                    |
| `TelaDetalhePeca.kt` | Tela 5 — detalhe de uma peça | **Léo**                     |
| `MainActivity.kt` | Junta tudo e faz a navegação | quem ficar de integrador(a) |

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

foi usado só o que apareceu no material da aula:
- `remember` / `mutableStateOf` para estado 
- `LazyColumn` com `items { }` 
- `OutlinedTextField`, `Card`, `Row`/`Column`, `Modifier.clickable`
- `data class`, `when`, `filter`/`forEach`/`take`, funções com parâmetros
  do tipo função (lambdas)
- `Scaffold` com `bottomBar`

## O que foi simplificado em relação ao mockup

O design original tinha mais coisas do que o pedido
mínimo (gráfico de pizza, alertas de garantia, modal de
cadastro, etc). Deixamos de fora o que dependua de recursos mais avançados
(desenho de gráfico customizado) e mantivemos a essência de cada
tela com componentes básicos do Compose.

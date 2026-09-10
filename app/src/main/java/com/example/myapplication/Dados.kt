package com.example.myapplication

// ==== Dados de exemplo ====
// Simula um "banco de dados" fixo, para a equipe poder testar as telas
// sem precisar de banco de dados de verdade (não foi visto em aula).
object Dados {

    val pecas = listOf(
        Peca(
            id = "p1",
            nome = "RTX 4090 ROG Strix OC",
            categoria = "GPU",
            marca = "ASUS",
            modelo = "24GB GDDR6X",
            status = "Instalada",
            localizacao = "PC Gamer Principal",
            preco = "R$ 14.299",
            dataCompra = "12/03/2025",
            numeroSerie = "SN-4090-AX7742",
            loja = "Kabum",
            computador = "PC Gamer Principal",
            especificacoes = listOf(
                "Memória" to "24 GB GDDR6X",
                "Barramento" to "384 bits",
                "TDP" to "450 W",
                "Saídas" to "3x DP / 2x HDMI"
            ),
            historico = listOf(
                RegistroManutencao("Limpeza e repasta térmica", "14/06/2026", "Interna"),
                RegistroManutencao("Instalada no PC Gamer", "12/03/2025", "Interna")
            )
        ),
        Peca(
            id = "p2",
            nome = "AMD Ryzen 7 7800X3D",
            categoria = "CPU",
            marca = "AMD",
            modelo = "AM5 - 8 núcleos",
            status = "Instalada",
            localizacao = "PC Gamer Principal",
            preco = "R$ 2.849",
            dataCompra = "02/03/2025",
            numeroSerie = "SN-7800X3D-QQ11",
            loja = "Terabyte",
            computador = "PC Gamer Principal",
            especificacoes = listOf(
                "Núcleos / Threads" to "8 / 16",
                "Clock boost" to "5.0 GHz",
                "Socket" to "AM5",
                "TDP" to "120 W"
            )
        ),
        Peca(
            id = "p3",
            nome = "Intel Core i9-14900K",
            categoria = "CPU",
            marca = "Intel",
            modelo = "LGA1700 - 24 núcleos",
            status = "Disponível",
            localizacao = "Prateleira A - Gaveta 2",
            preco = "R$ 3.199",
            dataCompra = "28/01/2025",
            numeroSerie = "SN-14900K-LM03",
            loja = "Pichau",
            especificacoes = listOf(
                "Núcleos" to "8P + 16E",
                "Threads" to "32",
                "Socket" to "LGA 1700",
                "TDP máx." to "253 W"
            )
        ),
        Peca(
            id = "p4",
            nome = "Kingston Fury Beast 32GB",
            categoria = "Memória RAM",
            marca = "Kingston",
            modelo = "DDR5 6000 CL36",
            status = "Instalada",
            localizacao = "PC Gamer Principal",
            preco = "R$ 899",
            dataCompra = "02/03/2025",
            numeroSerie = "SN-FURY32-4471",
            loja = "Kabum",
            computador = "PC Gamer Principal",
            especificacoes = listOf(
                "Capacidade" to "2x 16 GB",
                "Velocidade" to "6000 MT/s",
                "Latência" to "CL36"
            )
        ),
        Peca(
            id = "p5",
            nome = "RTX 4070 Super Gaming OC",
            categoria = "GPU",
            marca = "Gigabyte",
            modelo = "12GB GDDR6X",
            status = "Manutenção",
            localizacao = "Bancada - reparo",
            preco = "R$ 4.799",
            dataCompra = "08/11/2024",
            numeroSerie = "SN-4070S-VB21",
            loja = "Kabum",
            especificacoes = listOf(
                "Memória" to "12 GB GDDR6X",
                "TDP" to "220 W",
                "Status" to "RMA aberto",
                "Sintoma" to "Artefatos em jogo"
            ),
            historico = listOf(
                RegistroManutencao("RMA aberto - artefatos", "09/08/2026", "Kabum"),
                RegistroManutencao("Diagnóstico: VRAM instável", "05/08/2026", "Interna")
            )
        ),
        Peca(
            id = "p6",
            nome = "WD Black SN770 1TB",
            categoria = "SSD / HD",
            marca = "Western Digital",
            modelo = "M.2 NVMe Gen4",
            status = "Reservada",
            localizacao = "Prateleira A - Gaveta 1",
            preco = "R$ 559",
            dataCompra = "03/08/2025",
            numeroSerie = "SN-SN770-1TB-77",
            loja = "Kabum",
            especificacoes = listOf(
                "Capacidade" to "1 TB",
                "Interface" to "PCIe 4.0 x4",
                "Leitura" to "5.150 MB/s"
            )
        ),
        Peca(
            id = "p7",
            nome = "NZXT Kraken 360 RGB",
            categoria = "Cooler",
            marca = "NZXT",
            modelo = "AIO 360 mm",
            status = "Disponível",
            localizacao = "Prateleira B - Caixa 4",
            preco = "R$ 1.099",
            dataCompra = "22/05/2025",
            numeroSerie = "SN-KRK360-7781",
            loja = "Terabyte",
            especificacoes = listOf(
                "Radiador" to "360 mm",
                "Ventoinhas" to "3x 120 mm",
                "Sockets" to "AM5 / LGA1700"
            )
        )
    )

    val computadores = listOf(
        Computador(
            nome = "PC Gamer Principal",
            status = "Ativo",
            compatibilidade = "Compatível",
            compativel = true,
            valorTotal = "R$ 23.6k",
            consumo = "~690 W",
            dataMontagem = "03/2025",
            slots = listOf(
                Slot("CPU", "Ryzen 7 7800X3D"),
                Slot("Cooler", "Kraken 360 RGB"),
                Slot("Placa-mãe", "ROG Strix B650E-F"),
                Slot("GPU", "RTX 4090 Strix"),
                Slot("Memória", "Fury Beast 32GB"),
                Slot("Armazenamento", "990 Pro 2TB"),
                Slot("Fonte", "Corsair RM850x"),
                Slot("Gabinete", "O11 Dynamic EVO")
            )
        ),
        Computador(
            nome = "Workstation Render",
            status = "Em manutenção",
            compatibilidade = "Fonte insuficiente",
            compativel = false,
            valorTotal = "R$ 16.9k",
            consumo = "~780 W",
            dataMontagem = "09/2025",
            slots = listOf(
                Slot("CPU", "Core i9-14900K"),
                Slot("Cooler", "Noctua NH-D15"),
                Slot("Placa-mãe", "MAG Z790 Tomahawk"),
                Slot("GPU", "RTX 4070 S (reparo)"),
                Slot("Memória", "Vengeance 64GB"),
                Slot("Armazenamento", "990 Pro 1TB"),
                Slot("Fonte", ""),
                Slot("Gabinete", "Fractal North XL")
            )
        ),
        Computador(
            nome = "Home Server / NAS",
            status = "Ativo",
            compatibilidade = "Compatível",
            compativel = true,
            valorTotal = "R$ 6.4k",
            consumo = "~180 W",
            dataMontagem = "01/2026",
            slots = listOf(
                Slot("CPU", "Ryzen 5 5600"),
                Slot("Cooler", "Wraith Stealth"),
                Slot("Placa-mãe", "ASRock B550M Pro4"),
                Slot("GPU", ""),
                Slot("Memória", "Crucial 32GB DDR4"),
                Slot("Armazenamento", "4x IronWolf 8TB"),
                Slot("Fonte", "Focus GX-650"),
                Slot("Gabinete", "Fractal Node 804")
            )
        )
    )

    val desejos = listOf(
        Desejo("RTX 5080 Gaming Trio", "GPU", "PC Gamer Principal", "Alta", "R$ 9.899", "R$ 11.499", "-14%", true, "Kabum"),
        Desejo("Fonte Corsair RM1000x", "Fonte", "Workstation Render", "Alta", "R$ 1.349", "R$ 1.499", "-10%", true, "Terabyte"),
        Desejo("Samsung 990 Pro 4TB", "SSD / HD", "Home Server", "Média", "R$ 2.599", "R$ 2.449", "+6%", false, "Amazon"),
        Desejo("Corsair Vengeance 64GB DDR5", "Memória RAM", "Workstation Render", "Média", "R$ 1.789", "R$ 1.890", "-5%", true, "Pichau"),
        Desejo("LG UltraGear 32GS95UE", "Monitor", "Estação 1", "Baixa", "R$ 7.499", "R$ 7.999", "-6%", true, "Loja oficial LG"),
        Desejo("Noctua NH-D15 G2", "Cooler", "Estoque", "Baixa", "R$ 899", "R$ 949", "-5%", true, "Terabyte")
    )
}


// Mapa de bindings globais
def globals = [:]

// defines a sample LifeCycleHook that prints some output to the Gremlin Server console.
// note that the name of the key in the "global" map is unimportant.
globals << [hook : [
        onStartUp: { ctx ->
            ctx.logger.info("Executed once at startup of Gremlin Server.")
        },
        onShutDown: { ctx ->
            ctx.logger.info("Executed once at shutdown of Gremlin Server.")
        }
] as LifeCycleHook]

// Expõe traversal sources para cada grafo declarado no YAML
// As variáveis g1 e g2 aqui referem-se às chaves declaradas em "graphs" no gremlin-server.yaml
globals << [g1 : graph.traversal()]
globals << [g2 : graph2.traversal()]

// Opcional: hooks de ciclo de vida, logs, funções utilitárias podem ser adicionados aqui
// globals << [hook : [ onStartUp: { ctx -> ctx.logger.info("Server iniciado.") } ] as LifeCycleHook]

// Retorna o mapa para que o Gremlin Server registre os bindings
return globals

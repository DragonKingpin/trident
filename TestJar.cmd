PATH=%PATH%D:\ProgramFiles\ToolChains\Java\jdk11x64\bin;
START java -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 --add-opens java.base/sun.net.www.protocol.jar=ALL-UNNAMED -jar ./Saurons/Saurye/target/saurye-2.1.0.jar --server.port=8000
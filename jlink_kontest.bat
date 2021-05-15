::@echo off

set folder=".\jlink_target"
cd /d %folder%
for /F "delims=" %%i in ('dir /b') do (rmdir "%%i" /s/q || del "%%i" /s/q)
cd ..

:: win 
jlink --output ./jlink_target/KonTest_win --module-path "C:\Program Files\Java\jdk-14.0.2\jmods";".\target\KonzentrationsTest-0.0.7.jar";".\runtimes\win\javafx-jmods-11.0.2" --add-modules konzentrationstest,javafx.controls,javafx.fxml --launcher launch=konzentrationstest/general.Main

:: linux
jlink --output ./jlink_target/KonTest_linux --module-path ".\runtimes\linux\jdk-14.0.2\jmods";".\target\KonzentrationsTest-0.0.7.jar";".\runtimes\linux\javafx-jmods-11.0.2" --add-modules konzentrationstest,javafx.controls,javafx.fxml --launcher launch=konzentrationstest/general.Main

:: mac
jlink --output ./jlink_target/KonTest_mac --module-path ".\runtimes\mac\jdk-14.0.2.jdk\Contents\Home\jmods";".\target\KonzentrationsTest-0.0.7.jar";".\runtimes\mac\javafx-jmods-11.0.2" --add-modules konzentrationstest,javafx.controls,javafx.fxml --launcher launch=konzentrationstest/general.Main

::pause
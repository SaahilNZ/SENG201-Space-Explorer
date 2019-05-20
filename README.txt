SENG201 - Space Explorer
Created by Saahil Hari (skh154) and Isaac Walton (ijw21)

Building the project

1. Open the project folder in Eclipse
2. Click on File -> Export
3. Expand the 'Java' folder and select 'Runnable JAR File', then click 'Next' button
4. Under 'Launch configuration', select 'StartScreen - Space Explorer'
5. Under 'Export destination', click the 'Browse' button and select a name and location to save the JAR file
6. Under 'Library handling', select 'Extract required libraries into generated JAR'
7. Click the 'Finish' button
8. Click 'OK' on any dialogs that appear

Running the game

1. Open a terminal and navigate to the folder in which the exported JAR is located
2. In the terminal, type in the following command and press enter:
    java -jar <FileName.jar>
    Replacing '<FileName.jar>' with the name of the exported JAR file
    i.e. 'java -jar SpaceExplorer.jar'
# AndroidDriller

## About

AndroidDriller is a tool that extends the RepoDriller() tool for mining android repositories.


## Running the tool

To run it just execute the androidDriller.jar file.

The repositories URLs expected to be analyzed should be listed in separated lines inside androidDriller/input/repoURLs.in file.

The outputs will be in separate folders inside androidDriller/output folder. The folders names will match the repositories names.

To track the progress of the tool, a file will be created at the same directory of the androidDriller.jar execution with the  name 'n_t', where n is the order of the repository being analyzed and t is the total quantity of repositories listed in the androidDriller/input/repoURLs.in file.


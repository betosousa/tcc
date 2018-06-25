import os

outputdir = 'teste'
file_separator = '/'

def get_dirs():
	return [t[1] for t in os.walk(outputdir)][0]

def count_lines(folder):
	lines = sum(1 for line in open(folder + file_separator + 'activityDriller.csv', 'r'))
	return lines

if __name__ == '__main__':
	dirs = get_dirs()
	commits = 0
	for folder in dirs:
		commits = commits + count_lines(outputdir + file_separator + folder)
	print (commits)
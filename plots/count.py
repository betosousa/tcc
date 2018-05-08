def plot_history(component_type, repoName):
	file_component_type = repoName + component_type
	add = []
	rem = []
	mod = []
	dates = []
	f = open(file_component_type + 'Driller.csv', 'r')
	for line in f:
		commit = line.split(',')
		add.append(int(commit[0]))
		rem.append(int(commit[1]))
		mod.append(int(commit[2]))
		dates.append(commit[3])
	f.close()

	print "{} -> {} +{} -{} = {} ({})".format(repoName, component_type, sum(add), sum(rem), sum(add) - sum(rem), sum(mod))

def plot_repo(repo):
	repoName = repo + '\\'
	plot_history('activity', repoName)
	plot_history('service', repoName)
	plot_history('broadcastReceiver', repoName)
	plot_history('contentProvider', repoName)
	plot_history('permission', repoName)
	plot_history('usesPermission', repoName)


if __name__ == '__main__':
	repo = raw_input()
	while repo != '##':
		if '#' not in repo:
			plot_repo(repo)
			print '\n'
		repo = raw_input()
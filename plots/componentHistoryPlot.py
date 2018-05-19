import plotly.offline as plotly
import plotly.graph_objs as graph
import sys

def plot_history(component_type, repoName):
	add = []
	rem = []
	mod = []
	dates = []
	total = []
	component_type = repoName + component_type
	f = open(component_type + 'Driller.csv', 'r')
	for line in f:
		commit = line.split(',')
		add.append(int(commit[0]))
		rem.append(int(commit[1]))
		mod.append(int(commit[2]))
		total.append(int(commit[3]))
		dates.append(commit[4])
	f.close()
	addBar = graph.Bar(x = dates, y = add, name = 'Added', marker = dict(color='green'), text = add, textposition = 'outside') 
	remBar = graph.Bar(x = dates, y = [-r for r in rem], name = 'Removed', marker = dict(color='red'), text = rem, textposition = 'outside')
	modBar = graph.Bar(x = dates, y = mod, name = 'Modified', marker = dict(color='blue'), text = mod, textposition = 'outside') 
	totalLine = graph.Scatter(x = dates, y = total, name = 'Total', mode = 'lines+markers', line = dict(color='orange'))
	data = [addBar, remBar, modBar, totalLine]
	layout = graph.Layout(
		title = component_type + ' Commit History',
		barmode = 'relative',
		)
	figure = graph.Figure(data = data, layout = layout)
	plotly.plot(figure, image='png', auto_open=True,
				filename = component_type + '_history.html',
				image_filename = component_type.replace('\\','_') + '_history')

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
		repo = raw_input()
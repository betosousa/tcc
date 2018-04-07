import plotly.offline as plotly
import plotly.graph_objs as graph
import sys

repoName = ''

def plot_history(component_type):
	add = []
	rem = []
	mod = []
	dates = []
	component_type = repoName + component_type
	f = open(component_type + 'Driller.csv', 'r')
	for line in f:
		commit = line.split(',')
		add.append(int(commit[0]))
		rem.append(int(commit[1]))
		mod.append(int(commit[2]))
		dates.append(commit[3])
	f.close()
	addBar = graph.Bar(x = dates, y = add, name = 'Added', marker = dict(color='green'), text = add, textposition = 'outside') 
	remBar = graph.Bar(x = dates, y = [-r for r in rem], name = 'Removed', marker = dict(color='red'), text = rem, textposition = 'outside')
	modBar = graph.Bar(x = dates, y = mod, name = 'Modified', marker = dict(color='blue'), text = mod, textposition = 'outside') 
	data = [addBar, remBar, modBar]
	layout = graph.Layout(
		title = component_type + ' Commit History',
		barmode = 'relative',
		)
	figure = graph.Figure(data = data, layout = layout)
	plotly.plot(figure, image='png', auto_open=True,
				filename = component_type + '_history.html',
				image_filename = component_type.replace('\\','_') + '_history')


if __name__ == '__main__':
	repoName = sys.argv[1] + '\\'
	plot_history('activity')
	plot_history('service')
	plot_history('broadcastReceiver')
	plot_history('contentProvider')
	plot_history('permission')
	plot_history('usesPermission')

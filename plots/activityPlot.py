import plotly.offline as plotly
import plotly.graph_objs as graph

add = []
rem = []
dates = []

f = open('input.csv', 'r')
for line in f:
	commit = line.split(',')
	add.append(int(commit[0]))
	rem.append(-int(commit[1]))
	dates.append(commit[2])
f.close()
 
addBar = graph.Bar(x = dates, y = add, name = 'Added', marker = dict(color='green')) 
remBar = graph.Bar(x = dates, y = rem, name = 'Removed', marker = dict(color='red'))
data = [addBar, remBar]

layout = graph.Layout(
	title = 'Activities Commit History',
	barmode='group',
	)

figure = graph.Figure(data = data, layout = layout)

plotly.plot(figure, image='png', auto_open=False,
			filename = 'activity_history.html',
			image_filename='activity_history')
			
import Vertex
import Graph
import Color

# Parses a Makeflow file and populates a graph with targets and dependencies
def parse_makeflow(file_name):
    for line in file:
        if not line.startswith((' ', '\t', '\n')) and line.find(':') != -1:
            split_line = line.split(':')
            targets = split_line[0]
            targets = targets.split()
            dependencies = split_line[1]
            dependencies = dependencies.split()

            for target in targets:
                if not g.contains_vertex(target):
                    target_vertex = Vertex.Vertex(target, Color.Color.white)
                else:
                    target_vertex = g.get_vertex(target)
                dependency_set = set([])
                for dependency in dependencies:
                    # add the dependency to graph if it is not there
                    if not g.contains_vertex(dependency):
                        dep_vertex = Vertex.Vertex(dependency, Color.Color.white)
                        adj_vertices = set([])
                        g.add_vertex(dependency, dep_vertex, adj_vertices)
                    else: # get the existing vertex
                        dep_vertex = g.get_vertex(dependency)
                    dependency_set.add(dep_vertex)
                # add target to graph
                g.add_vertex(target, target_vertex, dependency_set)

g = Graph.Graph()

print("Enter the name of your file:")
file_name = input()
file = open(file_name, "r")

parse_makeflow(file)

result_tuple = g.topological_sort()

if result_tuple[0]:
    print('Slow down big guy, you got a circular dependency...')
else: 
    print('You good homie, happy coding!')

print('\nHere is your topological list:\n')
for i in range(result_tuple[1].qsize()):
    print(result_tuple[1].get().id, end=" -> ")
print('Done!')
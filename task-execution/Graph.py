import Color

class Graph:
    pass

    # Default contructor, initializes empty dictionaries
    def __init__(self) -> None:
        self.vertex_dict = {}
        self.edge_dict = {}

    # Adds vertex to graph
    def add_vertex(self, key, vertex, adjacent_vert) -> None:
        self.vertex_dict[key] = vertex
        self.edge_dict[vertex.id] = adjacent_vert

    # Checks if a vertex is in the graph given an id
    def contains_vertex(self, key):
        return (key in self.vertex_dict)

    def get_vertex(self, key):
        return self.vertex_dict[key]
    
    def get_adjacent_vertices(self, key):
        return self.edge_dict[key]

    def print_info(self):
        print(self.vertex_dict)
        print(self.edge_dict)

    # Does a depth-first traversal of the graph until it is finshed (returns False)
    # or has detected a cycle (returns True)
    def find_cycles(self):
        has_cycle = False
        for vertex in self.vertex_dict.values():
            vertex.color = Color.Color.white
        for vertex in self.vertex_dict.values():
            if vertex.color == Color.Color.white:
                if self.visit(vertex):
                    has_cycle = True

                
        return has_cycle

    # Visits a node and will call itself until it has finished (returns False) or has detected
    # a circular dependency (returns True)
    def visit(self, vertex):
        vertex.color = Color.Color.grey
        adjacent_vertices = self.edge_dict[vertex.id]
        for adj_vertex in adjacent_vertices:
            if adj_vertex.color == Color.Color.white:
                if self.visit(adj_vertex):
                    return True
            elif adj_vertex.color == Color.Color.grey:
                return True
        vertex.color = Color.Color.black
        return False


    
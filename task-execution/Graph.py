import Color

class Graph:
    pass

    def __init__(self) -> None:
        self.vertex_dict = {}
        self.edge_dict = {}

    def add_vertex(self, key, vertex, adjacent_vert) -> None:
        self.vertex_dict[key] = vertex
        self.edge_dict[vertex.id] = adjacent_vert

    def contains_vertex(self, key):
        return (key in self.vertex_dict)

    def get_vertex(self, key):
        return self.vertex_dict[key]
    
    def get_adjacent_vertices(self, key):
        return self.edge_dict[key]

    def print_info(self):
        print(self.vertex_dict)
        print(self.edge_dict)

    def find_cycles(self):
        has_cycle = False
        for vertex in self.vertex_dict.values():
            vertex.color = Color.Color.white
        for vertex in self.vertex_dict.values():
            if vertex.color == Color.Color.white:
                if self.visit(vertex):
                    has_cycle = True

                
        return has_cycle

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


    
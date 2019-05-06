class Graph:
    pass

    def __init__(self) -> None:
        self.vertex_dict = {}
        self.edge_dict = {}

    def add_vertex(self, key, vertex, adjacent_vert) -> None:
        self.vertex_dict[key] = vertex
        adj_set = self.adjacent_vert[vertex.id]
        adj_set.add(vertex)
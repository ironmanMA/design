# !/usr/bin/env python
# take Root Left Right from file, print inorder, debug given code
# a:b:
# b::c
# c:d:
# d::
# prints b d c a

import sys

all_nodes = {}


class Node:
    def __init__(self, name):
        self.name = name
        self.left = None
        self.right = None


def get_node(name):
    node = all_nodes.get(name, Node(name))
    all_nodes[name] = node
    # print node.name
    return node


def load():
    root = None
    count = 0
    for line in sys.stdin:
        p, l, r = line.strip().split(':')
        # print line,line.strip().split(':'), p,l,r,get_node(p)
        # print get_node(p)
        p_node = get_node(p)
        if count < 1:
            root = p_node
            count = 1
        l_node = get_node(l)
        r_node = get_node(r)
        p_node.left = l_node
        p_node.right = r_node

    return root


def traverse_print(root):
    # In-order traversal
    if root is not None:
        traverse_print(root.left)
        print root.name
        traverse_print(root.right)


if __name__ == '__main__':
    root = load()
    traverse_print(root)

package org.knime.example.node;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "MyExampleNode" Node.
 * This is an example node provided by KNIME.
 *
 * @author KNIME AG, Zurich, Switzerland
 */
public class MyExampleNodeNodeFactory 
        extends NodeFactory<MyExampleNodeNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MyExampleNodeNodeModel createNodeModel() {
        return new MyExampleNodeNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<MyExampleNodeNodeModel> createNodeView(final int viewIndex,
            final MyExampleNodeNodeModel nodeModel) {
        return new MyExampleNodeNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new MyExampleNodeNodeDialog();
    }

}

